package org.toys.models;

public class Toy {
    private Integer id;
    private String name;
    private Integer count;
    private Integer weight;

    public Toy(Integer id, String name, Integer count, Integer weight) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", weight=" + weight;
    }
}
