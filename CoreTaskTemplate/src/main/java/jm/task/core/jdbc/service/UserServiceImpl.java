package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends UserDaoHibernateImpl implements UserService {
    public UserServiceImpl() {}

    @Override
    public void createUsersTable()  {
        super.createUsersTable();
    }

    @Override
    public void dropUsersTable()  {
        super.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        super.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(Long id) throws SQLException {

    }

    @Override
    public void removeUserById(long id) {
        super.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return super.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        super.cleanUsersTable();
    }
}