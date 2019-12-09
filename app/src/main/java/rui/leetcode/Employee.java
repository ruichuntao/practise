package rui.leetcode;

import java.util.HashMap;
import java.util.List;

public class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;

    int ans = 0;

    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        getImportanceDiGui(map.get(id), map);
        return ans;
    }

    public void getImportanceDiGui(Employee employee, HashMap<Integer, Employee> map) {
        if (employee.subordinates.size() == 0)
            return;
        for (Integer subordinate : employee.subordinates) {
            ans += map.get(subordinate).importance;
            getImportanceDiGui(map.get(subordinate), map);
        }
    }

    public static void main(String[] args) {
        Employee e = new Employee();
        e.id = 1;
    }
}
