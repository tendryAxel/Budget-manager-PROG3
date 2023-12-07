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
            String sql = "SELECT * FROM \"transaction\"";
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
            String sql = "INSERT INTO \"transaction\" (value,description,id_account,transaction_date) VALUES(?,?,?,?)";
            List<TransactionModel> SaveTransaction = new ArrayList<>();
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                for (TransactionModel transactionModel : toSave){
                    preparedStatement.setInt(1,transactionModel.getValue());
                    preparedStatement.setString(2,transactionModel.getDescription());
                    preparedStatement.setInt(3,transactionModel.getId_account());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(transactionModel.getTransaction_date()));
                }
            }
            catch (SQLException e){
                throw new RuntimeException();
            }
            return SaveTransaction;
        }

        @Override
        public static int save(TransactionModel toSave)  {
            String sql = "INSERT INTO \"transaction\" (value,description,id_account,transaction_date) VALUES(?,?,?,?) RETURNING id";
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                preparedStatement.setInt(1,toSave.getValue());
                preparedStatement.setString(2,toSave.getDescription());
                preparedStatement.setInt(3,toSave.getId_account());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(toSave.getTransaction_date()));
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                return resultSet.getInt("id");
            }
            catch (SQLException e){
                throw new RuntimeException();
            }
            return 0;
        }


        public List<TransactionModel> findAllByIdAccountAndDate(int id, LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
            String sql = "SELECT * FROM \"transaction\" " +
                    "INNER JOIN \"account\" " +
                    "ON \"transaction\".id_account = \"account\".id " +
                    "WHERE \"account\".id = ?" +
                    "AND \"transaction\".transaction_date BETWEEN ? AND ? " +
                    "ORDER BY \"transaction\".transaction_date DESC ";
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TransactionModel> transactions = new ArrayList<>();

            while (resultSet.next()){
                transactions.add(new TransactionModel(
                        resultSet.getInt("\"transaction\".id"),
                        resultSet.getInt("value"),
                        resultSet.getString("description"),
                        resultSet.getInt("id_account"),
                        resultSet.getTimestamp("transaction_date").toLocalDateTime()
                ));
            }

            return transactions;
        }
}
