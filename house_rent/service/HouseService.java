package house_rent.service;

import house_rent.domain.House;

//业务层，实际处理数据
public class HouseService {
    private House[] houses;
    private int currentId = 0;

    //这里的size和capacity的含义与C++ STL 中的相应成员意思一致
    private int size = 0;
    private int capacity = 10;

    public int getSize() {
        return size;
    }

    public HouseService() {
        houses = new House[capacity];
    }

    public House[] list() {
        return houses;
    }

    public boolean add(House newHouse) {
        if (size == capacity) {
            return false;
        }
        houses[size++] = newHouse;
        newHouse.setId(++currentId);
        return true;
    }


    /**
     * 根据id查找houses中的对象，
     * 找到则返回该对象，
     * 找不到返回null
     * @param id
     * @return House
     */
    public House find(int id) {
        //鉴于houses中各对象的id是升序排列，用二分查找效率更高
        for (int i = 0; i < size; ++i) {
            if (houses[i].getId() == id)
                return houses[i];
        }
        return null;
    }

    public boolean del(int id) {
        int index = -1;
        for (int i = 0; i < size; ++i) {
            if (houses[i].getId() == id) {
                index = i;
                break;
            }
        }
        //该id不存在
        if (index == -1) {
            return false;
        }

        for (; index < size - 1; ++index) {
            houses[index] = houses[index + 1];
        }
        houses[index] = null;
        --size;

        return true;

    }


}
