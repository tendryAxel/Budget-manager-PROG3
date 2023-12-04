package operation;

import model.CurrencyModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperations implements CrudOperations<CurrencyModel> {
    @Override
    public List<CurrencyModel> findAll() throws SQLException {
        ResultSet resultSet = connectionDB.getConnection().prepareStatement("SELECT * FROM \"currency\"").executeQuery();
        List<CurrencyModel> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new CurrencyModel(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            ));
        }
        return result;
    }

    @Override
    public CurrencyModel save(CurrencyModel toSave) throws SQLException {
      PreparedStatement state = connectionDB.getConnection().prepareStatement("INSERT INTO \"currency\"(name) VALUES(?)");
      state.setString(1, toSave.getName());
      state.executeUpdate();
      return toSave;
    }

    @Override
    public List<CurrencyModel> saveAll(List<CurrencyModel> toSave) throws SQLException {
        for (CurrencyModel currency : toSave){
            save(currency);
        }
        return toSave;
    }
}
