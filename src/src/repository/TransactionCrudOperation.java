package repository;
import model.TransactionModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

    public class TransactionCrudOperation implements CrudOperations<TransactionModel>{
  public static void FunctionTransaction(AccountModel accountModel , TransactionModel transaction){
        switch (transaction.getType()){
               case CREDIT :
                BigDecimal updatedBalance = accountModel.getBalance().add(transaction.getAmount()) ;
                try{
                    String sql = "INSERT INTO transaction (label , amount , transaction_date ,type ,id_account) " +
               
                           "VALUES (?, ?, ?, ?, ?) " +
                            "ON CONFLICT (id) " +
                            "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                            "type = EXCLUDED.type, transaction_date = EXCLUDED.transaction_date , id_account = EXCLUDED.id_account";
                    PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql) ;
                    preparedStatement.setString(2 , transaction.getLabel());
                    preparedStatement.setBigDecimal(3,transaction.getAmount());
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(transaction.getTransaction_date()));
                    preparedStatement.setObject(4 , transaction.getType() , Types.OTHER);
                    preparedStatement.setInt(5,transaction.getId_account());
                    preparedStatement.executeUpdate();

                    String update = "UPDATE account SET balance = ? WHERE id_account = ?" ;
                    PreparedStatement conn = connectionDB.getConnection().prepareStatement(update); ;
                    conn.setBigDecimal(1  , updatedBalance);
                    conn.setInt(2 ,accountModel.getId());
                    conn.executeUpdate() ;

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                case DEBIT :
                BigDecimal debitedBalance = accountModel.getBalance().subtract(transaction.getAmount());
                try {
                    String sql = "INSERT INTO transaction (label , amount , transaction_date ,type ,id_account) " +
                            "VALUES (?, ?, ?, ?,?) " +
                            "ON CONFLICT (id) " +
                            "DO UPDATE SET amount = EXCLUDED.amount, label = EXCLUDED.label, " +
                            "type = EXCLUDED.type, transaction_date = EXCLUDED.transaction_date , id_account = EXCLUDED.id_account";
                    PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql);
                    preparedStatement.setString(2 , transaction.getLabel());
                    preparedStatement.setBigDecimal(3,transaction.getAmount());
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(transaction.getTransaction_date()));
                    preparedStatement.setObject(4 , transaction.getType() , Types.OTHER);
                    preparedStatement.setInt(5,transaction.getId_account());
                    preparedStatement.executeUpdate();

                    String update = "UPDATE account SET balance = ? WHERE id_account = ?" ;
                    PreparedStatement conn = connectionDB.getConnection().prepareStatement(update) ;
                    conn.setBigDecimal(1  , debitedBalance);
                    conn.setInt(2 ,accountModel.getId());
                    conn.executeUpdate() ;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        }
    }


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
        public TransactionModel save(TransactionModel toSave)  {
            String sql = "INSERT INTO \"transaction\" (value,description,id_account,transaction_date) VALUES(?,?,?,?)";
            try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
                preparedStatement.setInt(1,toSave.getValue());
                preparedStatement.setString(2,toSave.getDescription());
                preparedStatement.setInt(3,toSave.getId_account());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(toSave.getTransaction_date()));
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                throw new RuntimeException();
            }
            return toSave;
        }
}
