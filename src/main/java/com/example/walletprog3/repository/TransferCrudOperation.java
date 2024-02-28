package com.example.walletprog3.repository;
import com.example.walletprog3.utils.PreparedStatementStep;
import com.example.walletprog3.model.TransferModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class TransferCrudOperation extends CrudOperationsImpl<TransferModel> {
    public TransferCrudOperation(com.example.walletprog3.repository.connectionDB connectionDB) {
        super(connectionDB);
    }

    @Override
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
