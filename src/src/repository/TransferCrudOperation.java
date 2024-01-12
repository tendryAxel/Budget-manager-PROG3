package repository;
import model.TransferModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferCrudOperation extends CrudOperationsImpl<TransferModel> {
    @Override
    public List<TransferModel> findAll() throws SQLException {
        String sql = String.format(
                "SELECT * FROM \"%s\"",
                TransferModel.TABLE_NAME
        );
        ResultSet resultSet = connectionDB.getConnection().prepareStatement(sql).executeQuery();
        List<TransferModel> AllTransaction = new ArrayList<>();

        while (resultSet.next()){
            AllTransaction.add(new TransferModel(
                    resultSet.getInt(TransferModel.ID),
                    resultSet.getInt(TransferModel.ID_DEBIT),
                    resultSet.getInt(TransferModel.ID_CREDIT),
                    resultSet.getTimestamp(TransferModel.TRANSFER_DATE).toLocalDateTime()
            ));
        }

        return AllTransaction;
    }

    @Override
    public List<TransferModel> saveAll(List<TransferModel> toSave) {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s) VALUES(?,?)",
                TransferModel.TABLE_NAME,
                TransferModel.ID_DEBIT,
                TransferModel.ID_CREDIT
        );
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
    public TransferModel save(TransferModel toSave)  {
        String sql = String.format(
                "INSERT INTO \"%s\" (%s,%s) VALUES(?,?)",
                TransferModel.TABLE_NAME,
                TransferModel.ID_DEBIT,
                TransferModel.ID_CREDIT
        );
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
