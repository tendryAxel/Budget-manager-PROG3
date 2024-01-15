package repository;
import utils.PreparedStatementStep;
import model.TransferModel;
import java.sql.*;


public class TransferCrudOperation extends CrudOperationsImpl<TransferModel> {    @Override
public TransferModel createT(ResultSet resultSet) throws SQLException {
    return new TransferModel(
            resultSet.getInt(TransferModel.ID),
            resultSet.getInt(TransferModel.ID_DEBIT),
            resultSet.getInt(TransferModel.ID_CREDIT),
            resultSet.getTimestamp(TransferModel.TRANSFER_DATE).toLocalDateTime()
    );
}

    @Override
    public PreparedStatement createT(PreparedStatementStep pr, TransferModel model) throws SQLException {
        PreparedStatement preparedStatement = pr.getPreparedStatement();
        preparedStatement.setInt(1, model.getTransactionDebtor());
        preparedStatement.setInt(2, model.getTransactionCredit());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(model.getDatetime()));
        return preparedStatement;
    }

    @Override
    public Class<TransferModel> getClassT() {
        return TransferModel.class;
    }

    @Override
    public TransferModel findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public TransferModel delete(Integer id){
        return super.delete(id);
    }
}
