package operation;

import model.TransactionModel;
import repository.CrudOperations;
import repository.connectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperations implements CrudOperations<TransactionModel> {
    @Override
    public List<TransactionModel> findAll() throws SQLException {
        ResultSet resultSet = connectionDB.getConnection().prepareStatement("SELECT * FROM \"transaction\"").executeQuery();
        List<TransactionModel> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new TransactionModel(
                    resultSet.getInt("id"),
                    resultSet.getInt("value"),
                    resultSet.getString("description"),
                    resultSet.getInt("id_account"),
                    resultSet.getTimestamp("transaction_date").toLocalDateTime()
            ));
        }
        return result;
    }

    @Override
    public TransactionModel save(TransactionModel toSave) throws SQLException {
      PreparedStatement state = connectionDB.getConnection().prepareStatement("INSERT INTO \"transaction\"(value, description, id_account, transaction_date) VALUES(?, ?, ?, ?)");
      state.setInt(1, toSave.getValue());
      state.setString(2, toSave.getDescription());
      state.setInt(3, toSave.getId_account());
      state.setTimestamp(4, Timestamp.valueOf(toSave.getTransaction_date()));
      state.executeUpdate();
      return toSave;
    }

    @Override
    public List<TransactionModel> saveAll(List<TransactionModel> toSave) throws SQLException {
        for (TransactionModel currency : toSave){
            save(currency);
        }
        return toSave;
    }
}
