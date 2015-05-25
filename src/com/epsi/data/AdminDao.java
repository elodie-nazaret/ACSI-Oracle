package com.epsi.data;

import java.util.List;

public interface AdminDao {

    public Admin findById(int id);
    public List<Admin> findAll();
    public void insert(Admin admin);
    public void update(Admin admin);
    public void delete(Admin admin);
}
