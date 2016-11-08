package com.mdfazlerabbi.diulibrary;

import java.util.ArrayList;

/**
 * Created by Md. Fazle Rabbi on 4/17/2016.
 */
public interface UserModelInterface {

    public User getUserDetailsByUserId(String userId);

    public User getUserDetailsStartWithUserId(String userId);

    public ArrayList<User> getAllUsersList();

}