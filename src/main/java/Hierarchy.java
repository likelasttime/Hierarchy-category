import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Hierarchy {

    private static final long DEFAULT_LEFT_FOR_ROOT = 1;
    private static final long DEFAULT_RIGHT_FOR_ROOT = 2;
    private static final long DEFAULT_DEPTH = 1;

    private Category rootCategory;

    private Category parentCategory;

    private Long leftNode;

    private Long rightNode;

    private Long depth;

    public void updateChildCategory(Category parentCategory, Hierarchy childCategory){
        childCategory.rootCategory = this.rootCategory;
        childCategory.parentCategory = parentCategory;
        childCategory.depth = this.depth + 1L;
        childCategory.leftNode = this. rightNode;
        childCategory.rightNode = this.rightNode + 1;
    }

    public List<Hierarchy> getChildren(List<Hierarchy> categories) {
        List<Hierarchy> children = new ArrayList<>();
        if (this.rightNode - this.leftNode > 1) {       // 하위 자식 카테고리 존재
            for (Hierarchy category : categories) {
                //if (rootCategory != this.rootCategory) continue;
                if (category.leftNode > this.leftNode && category.rightNode < this.rightNode) {
                    children.add(category);
                }
            }
        }
        return children;
    }

    public void updateAsRoot(Category category) {
        this.rootCategory = category;
        this.leftNode = DEFAULT_LEFT_FOR_ROOT;
        this.rightNode = DEFAULT_RIGHT_FOR_ROOT;
        this.depth = DEFAULT_DEPTH;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public Hierarchy() {
        this(null, null, null, null, DEFAULT_DEPTH);
    }

    public Hierarchy(Category rootCategory, Category parentCategory, Long leftNode, Long rightNode, Long depth) {
        this.rootCategory = rootCategory;
        this.parentCategory = parentCategory;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.depth = depth;
    }

    public Category getRootCategory() {
        return rootCategory;
    }

    public Long getLeftNode() {
        return leftNode;
    }

    public Long getRightNode() {
        return rightNode;
    }

    public Long getDepth() {
        return depth;
    }
}
