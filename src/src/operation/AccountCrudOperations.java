package operation;

import model.AccountModel;
import repository.CrudOperations;
import repository.connectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperations implements CrudOperations<AccountModel> {
    @Override
    public List<AccountModel> findAll() throws SQLException {
        ResultSet resultSet = connectionDB.getConnection().prepareStatement("SELECT * FROM \"account\"").executeQuery();
        List<AccountModel> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new AccountModel(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("id_currency")
            ));
        }
        return result;
    }

    @Override
    public AccountModel save(AccountModel toSave) throws SQLException {
      PreparedStatement state = connectionDB.getConnection().prepareStatement("INSERT INTO \"account\"(name, id_currency) VALUES(?, ?)");
      state.setString(1, toSave.getName());
      state.setInt(2, toSave.getId_currency());
      state.executeUpdate();
      return toSave;
    }

    @Override
    public List<AccountModel> saveAll(List<AccountModel> toSave) throws SQLException {
        for (AccountModel Account : toSave){
            save(Account);
        }
        return toSave;
    }
}
