package operation;

import model.CurrencyModel;

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
    public CurrencyModel save(CurrencyModel toSave) {
      prepareStatement state = connectionDB.getConnection().prepareStatement("INSERT INTO \"currency\"(name) VALUES(?)");
      state.setString(1, toSave.getName());
      state.executeUpdate();
      return toSave;
    }

    @Override
    public List saveAll(List toSave) {
        return null;
    }
}
