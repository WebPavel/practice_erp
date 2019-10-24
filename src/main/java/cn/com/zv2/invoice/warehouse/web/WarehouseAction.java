package cn.com.zv2.invoice.warehouse.web;

import cn.com.zv2.auth.employee.entity.Employee;
import cn.com.zv2.auth.employee.service.EmployeeService;
import cn.com.zv2.invoice.warehouse.entity.Warehouse;
import cn.com.zv2.invoice.warehouse.entity.WarehouseQueryModel;
import cn.com.zv2.invoice.warehouse.service.WarehouseService;
import cn.com.zv2.util.base.BaseAction;

import java.util.List;

public class WarehouseAction extends BaseAction {

    public Long keeperId;
    public String keeperName;
    public Warehouse warehouse = new Warehouse();
    public WarehouseQueryModel warehouseQueryModel = new WarehouseQueryModel();
    private WarehouseService warehouseService;
    private EmployeeService employeeService;

    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String list() {
        Employee keeper = new Employee();
        keeper.setName(keeperName);
        warehouseQueryModel.setKeeper(keeper);
        setTotalRow(warehouseService.count(warehouseQueryModel));
        List<Warehouse> warehouseList = warehouseService.list(warehouseQueryModel, pageNum, pageSize);
        put("warehouseList", warehouseList);
        return LIST;
    }

    public String edit() {
        List<Employee> employeeList = employeeService.listByDepartment(getSessionEmployee().getDepartment().getId());
        put("employeeList", employeeList);
        if (warehouse.getId() != null) {
            warehouse = warehouseService.get(warehouse.getId());
        }
        return EDIT;
    }

    public String updateIfPresent() {
        Employee keeper = new Employee();
        keeper.setId(keeperId);
        warehouse.setKeeper(keeper);
        if (warehouse.getId() == null) {
            warehouseService.save(warehouse);
        } else {
            warehouseService.update(warehouse);
        }
        return REDIRECT_LIST;
    }

    public String delete() {
        warehouseService.delete(warehouse);
        return REDIRECT_LIST;
    }

    private Employee getSessionEmployee() {
        return (Employee) getSession(Employee.EMPLOYEE_LOGIN_USER_OBJECT_NAME);
    }
}
