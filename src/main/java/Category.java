import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Hierarchy hierarchy;

    public Category(Long id, String name, Hierarchy hierarchy) {
        this.id = id;
        this.name = name;
        this.hierarchy = hierarchy;
    }

    public void updateChildCategory(Category childCategory) {
        hierarchy.updateChildCategory(this, childCategory.hierarchy);
    }

    public void updateAsRoot() {
        hierarchy.updateAsRoot(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getLeftNode() {
        return hierarchy.getLeftNode();
    }

    public Long getRightNode() {
        return hierarchy.getRightNode();
    }

    public Category getRootCategory() {
        return hierarchy.getRootCategory();
    }
}
