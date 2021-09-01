package mockito;

public class WebService {
    public void login(String user, String password, Callback callback){
        if(checkLogin(user, password)){
            callback.onSuccess("Usuario Correcto");
        }else{
            callback.onFail("Error");
        }
    }

    public boolean checkLogin(String user, String password){
        return user.equals("Alberto") && password.equals("1234");
    }
}
