package models.components;


import java.lang.reflect.Constructor;

public class ComponentExploring {

    // Boundary Generic Type

    public <T extends LoginPage> void login(Class<T> loginPageClass, String usernameStr){
        // Wildcard Generic Type
        Class<?>[] parameters = new Class[]{};

        try{
            Constructor<T> constructor = loginPageClass.getConstructor(parameters);
            T loginPageObj = constructor.newInstance();
            loginPageObj.inputUserName(usernameStr);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
