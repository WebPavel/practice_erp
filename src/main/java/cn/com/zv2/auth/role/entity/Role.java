package cn.com.zv2.auth.role.entity;

import cn.com.zv2.auth.resource.entity.Resource;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lb
 * @date 2019/10/4 1:33
 */
public class Role {

    private Long id;
    private String name;
    private String code;
    private Set<Resource> resourceSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Resource> getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(Set<Resource> resourceSet) {
        this.resourceSet = resourceSet;
    }
}
