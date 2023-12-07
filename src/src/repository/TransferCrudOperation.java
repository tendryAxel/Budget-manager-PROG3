package repository;
import model.TransferModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferCrudOperation implements CrudOperations<TransferModel>{
    @Override
    public List<TransferModel> findAll() throws SQLException {
        String sql = "SELECT * FROM \"transfert\"";
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        List<TransferModel> AllTransaction = new ArrayList<>();

        while (resultSet.next()){
            AllTransaction.add(new TransferModel(
                    resultSet.getInt("id"),
                    resultSet.getInt("id_debtor"),
                    resultSet.getInt("id_credit"),
                    resultSet.getTimestamp("transfer_date").toLocalDateTime()
            ));
        }

        return AllTransaction;
    }

    @Override
    public List<TransferModel> saveAll(List<TransferModel> toSave) {
        String sql = "INSERT INTO \"transfert\" (id_debtor,id_credit) VALUES(?,?)";
        List<TransferModel> SaveTransaction = new ArrayList<>();
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            for (TransferModel transferModel : toSave){
                preparedStatement.setInt(1,transferModel.getTransactionDebtor());
                preparedStatement.setInt(2,transferModel.getTransactionCredit());
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return SaveTransaction;
    }

    @Override
    public static TransferModel save(TransferModel toSave)  {
        String sql = "INSERT INTO \"transfert\" (id_debtor,id_credit) VALUES(?,?)";
        try(PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1,toSave.getTransactionDebtor());
            preparedStatement.setInt(2,toSave.getTransactionCredit());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return toSave;
    }
}
