import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class Family {
    private int childrenCount = 0;
    private Human mother;
    private Human father;
    private Human[] children;
    private Pet pet;

    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;
        this.children = new Human[0];
        this.pet = new Pet();
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addChild(Human child) {
        if (children.length == childrenCount) {
            Human[] children2 = new Human[children.length + 1];
            System.arraycopy(children, 0, children2, 0, children.length);
            children = children2;
        }
        child.setFamily(this);
        children[childrenCount++] = child;
    }

    public boolean deleteChild(int index) {
        boolean result = false;
        Human[] newChild = new Human[children.length - 1];
        if (index == children.length - 1) {
            children[index].setFamily(null);
            for (int i = 0; i < newChild.length; i++) {
                newChild[i] = children[i];
            }
            children = newChild;
            result = true;
        } else if (index == 0) {
            children[index].setFamily(null);
            for (int i = 1; i <= newChild.length; i++) {
                newChild[i - 1] = children[i];
            }
            children = newChild;
            result = true;
        } else if (index >= 1 && index <= children.length - 2) {
            children[index].setFamily(null);
            System.arraycopy(children, 0, newChild, 0, index);
            if (children.length - 1 - index >= 0)
                System.arraycopy(children, index + 1, newChild, index, children.length - 1 - index);
            children = newChild;
            result = true;
        }
        return result;
    }

    public int countFamily() {
        if (this.children != null) {
            return 2 + this.getChildren().length;
        } else {
            return 2;
        }
    }

    public void greetPet() {
        System.out.println("Hello," + this.getPet().getNickname());
    }

    public void describePet() {
        StringBuilder slyLevel = new StringBuilder("");
        if (this.getPet().getTrickLevel1() > 50) {
            slyLevel.append("very sly");
        } else if (this.getPet().getTrickLevel1() < 50) {
            slyLevel.append("almost not sly");
        } else {
            slyLevel.append("sly");
        }
        System.out.println("I have a " + this.getPet().getSpecies() + ",he is " + this.getPet().getAge() + " years old,he is " + slyLevel);
    }


    public boolean feedPet(boolean check) {
        Random random = new Random();
        int randomTrickLevel = random.nextInt(100) + 1;
        if (randomTrickLevel > this.getPet().getTrickLevel1()) {
            System.out.println("I will feed " + this.getPet().getNickname());
            check = true;
        } else {
            System.out.println("I think " + this.getPet().getNickname() + " is not hungry.");
            check = false;
        }
        return check;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Family family = (Family) object;
        return Objects.equals(father, family.father)
                && Objects.equals(mother, family.mother)
                && Arrays.equals(children, family.children);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getMother(), getFather());
        result = result + Arrays.hashCode(getChildren());
        return result;
    }

    @Override
    public String toString() {
        return "Family{" +
                "childrenCount=" + childrenCount +
                ", mother=" + mother +
                ", father=" + father +
                ", children=" + Arrays.toString(children) +
                ", pet=" + pet +
                '}';
    }
}