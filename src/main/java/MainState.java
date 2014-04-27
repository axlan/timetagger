import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainState implements Serializable
{

    MainState() {
        this.nextId = 0;
        this.totalTime = 0;
        this.categories=new ArrayList<CategoryState>();
    }

    public void AddNewCategory(String name)
    {
        categories.add(new CategoryState(name,nextId++));
    }
    private int nextId;

    private long totalTime;

    private List<CategoryState> categories;

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public List<CategoryState> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryState> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "MainState [nextId=" + nextId + ", totalTime=" + totalTime + ", " +
                "categories=" + categories + "]";
    }

}

class CategoryState implements Serializable
{

    CategoryState(String name,int id) {
        this.totalTime = 0;
        this.id=id;
        this.name=name;
        this.active=false;
        this.startTime=0;
    }

    private int id;
    private String name;
    private long totalTime;
    private boolean active;
    private long startTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "CategoryState [id=" + id + ", name=" + name + ", " +
                "totalTime=" + totalTime +", active=" + active +", startTime=" + startTime +"]";
    }
}