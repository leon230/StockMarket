package com.stockmarket.validation;

import com.stockmarket.dao.UserDAO;
import com.stockmarket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Leon on 2016-11-04.
 */

@Component
public class UserValidation implements Validator {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;


        if(user.getUserName() == null || user.getUserName().equals("")){
            errors.rejectValue("userName", "NotEmpty.UserForm.userName");
        }
        if(!(user.getUserId() > 0) && userDAO.findUsername(user.getUserName()) > 0){
            errors.rejectValue("userName", "UserExists.UserForm.userName");
        }
        if(user.getUserName().length() > 50){
            errors.rejectValue("userName", "TooLong.UserForm.userName");
        }
        if(user.getUserPass() == null || user.getUserPass().equals("")){
            errors.rejectValue("userPass", "NotEmpty.UserForm.userPass");
        }
        for(int i = 0; i < user.getUserName().length(); i++){
            if((int) user.getUserName().toUpperCase().charAt(i) == 95 ||
                    ((int) user.getUserName().toUpperCase().charAt(i) >= 65 && (int) user.getUserName().toUpperCase().charAt(i) <=90) ||
                    ((int) user.getUserName().toUpperCase().charAt(i) >= 48 && (int) user.getUserName().toUpperCase().charAt(i) <=57)) {
            }
            else
            {
                errors.rejectValue("userName", "InvalidCharacters.UserForm.userName");
            }
            }
        }
//65-90
        //  48-57
        //95


}
// TODO add password confirmaion
