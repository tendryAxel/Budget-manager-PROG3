package repository;
import model.TransactionModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

    public class TransactionCrudOperation implements CrudOperations<TransactionModel>{
        @Override
        public List<TransactionModel> findAll() throws SQLException {
            String sql = "SELECT * FROM transaction";
            ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
            List<TransactionModel> AllTransaction = new ArrayList<>();

            while (resultSet.next()){
                AllTransaction.add(new TransactionModel(
                        resultSet.getInt("id"),
                        resultSet.getInt("value"),
                        resultSet.getString("description"),
                        resultSet.getInt("id_account"),
                        resultSet.getTimestamp("transaction_date").toLocalDateTime()
                ));
            }

            return AllTransaction;
        }

        @Override
        public List<TransactionModel> saveAll(List<TransactionModel> toSave) {
            String sql = "INSERT INTO transaction (id,value,description,id_account,transaction_date)";
            List<TransactionModel> SaveTransaction = new ArrayList<>();
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                for (TransactionModel transactionModel : toSave){
                    preparedStatement.setInt(1, transactionModel.getId());
                    preparedStatement.setInt(2,transactionModel.getValue());
                    preparedStatement.setString(3,transactionModel.getDescription());
                    preparedStatement.setInt(4,transactionModel.getId_account());
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(transactionModel.getTransaction_date()));
                }
            }
            catch (SQLException e){
                throw new RuntimeException();
            }
            return SaveTransaction;
        }

        @Override
        public TransactionModel save(TransactionModel toSave)  {
            String sql = "INSERT INTO transaction (id,value,description,id_account,transaction_date)";
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                preparedStatement.setInt(1,toSave.getId());
                preparedStatement.setInt(2,toSave.getValue());
                preparedStatement.setString(3,toSave.getDescription());
                preparedStatement.setInt(4,toSave.getId_account());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(toSave.getTransaction_date()));
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                throw new RuntimeException();
            }
            return toSave;
        }
}
