package io.nuls.ledger.entity.tx;

import io.nuls.core.chain.entity.BaseNulsData;
import io.nuls.core.constant.TransactionConstant;
import io.nuls.core.utils.io.NulsByteBuffer;
import io.nuls.core.utils.io.NulsOutputStreamBuffer;

import java.io.IOException;

/**
 * @author Niels
 * @date 2017/11/20
 */
public class TransferTransaction<T extends BaseNulsData> extends AbstractCoinTransaction<T> {
    public TransferTransaction() {
        super(TransactionConstant.TX_TYPE_TRANSFER);
    }

    public TransferTransaction(int type) {
        super(type);
    }

    @Override
    public int size() {
        //todo
        return 0;
    }

    @Override
    public void serializeToStream(NulsOutputStreamBuffer stream) throws IOException {
        //todo

    }

    @Override
    public void parse(NulsByteBuffer byteBuffer) {
        //todo

    }
}