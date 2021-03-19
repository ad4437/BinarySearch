// People.java

package binary;

public class People {
    private Person[] ppl;
    private int size = 0;
    public int comparisons = 0;

    public People(Person[] ppl) {
        this.ppl = ppl;
    }

    public People() {
        this.ppl = new Person[10];
    }

    public void resize() {
        Person[] ppl = new Person[size * 2];
        for (int i = 0; i < size; i++) {
            ppl[i] = this.ppl[i];
        }
        this.ppl = ppl;
    }

    public Person getPerson(int index) {
        return ppl[index];
    }

    public void addPerson(Person person) {
        if (size == ppl.length)
            resize();
        ppl[size] = person;
        size++;
    }

    public void deletePerson(int index) {
        for (int i = index; i < size; i++) {
            ppl[i] = (i != size - 1) ? ppl[i + 1] : ppl[i];
        }
        ppl[size - 1] = null;
        size--;
    }

    public void setPerson(Person person, int index) {
        ppl[index] = person;
    }

    public void setPersons(Person[] ppl) {
        this.ppl = ppl;
    }

    public Person[] getPeople() {
        return ppl;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void swap(int a, int b) {
        Person temp = ppl[a];
        ppl[a] = ppl[b];
        ppl[b] = temp;
    }

    public Person[] sortByName() {
        // Selection Sort Implementation
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (ppl[j].getName().compareToIgnoreCase(ppl[min].getName()) < 0)
                    min = j;
            }
            swap(i, min);
        }
        return ppl;
    }

    public int binarySearch(String name) {
        comparisons = 0;
        int L = 0;
        int R = size - 1;

        while (L <= R) {
            comparisons++;
            int mid = (L + R) / 2;
            if (ppl[mid].getName().equals(name))
                return mid;
            else if (ppl[mid].getName().compareToIgnoreCase(name) > 0)
                R = mid - 1;
            else
                L = mid + 1;
        }
        return -1;
    }

    public int linearSearch(String name) {
        comparisons = 0;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (ppl[i].getName().equals(name))
                return i;
        }
        return -1;
    }

    public int maxNamelen() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (ppl[i].getName().length() > max)
                max = ppl[i].getName().length();
        }
        if (max < 5)
            return 5;
        return max;
    }

    public String spaces(int max, int current) {
        String str = "";
        for (int i = 0; i < (max - current); i++) {
            str += " ";
        }
        return str;
    }

    public String print() {
        String str = "";
        int max = maxNamelen();
        str += "Name:" + spaces(max + 5, 5);
        str += "Age:\n";

        for (int i = 0; i < size; i++) {
            str += ppl[i].getName() + spaces(max + 5, ppl[i].getName().length());
            str += ppl[i].getAge() + "\n";
        }
        return str;
    }

}