package com.tianqi.common.result.rest.builder;

import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.entity.RestEntity;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.result.rest.entity.ValidateEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuantianqi
 */
public abstract class ResultBuilder<T, Builder extends ResultBuilder> {
    private StatusEnum status;
    private List<ValidateEntity> validates = new ArrayList<>();
    private BaseException error;
    private RestEntity<T> data;


    public Builder withStatus(StatusEnum status) {
        this.status = status;
        return (Builder) this;
    }

    public Builder withValidate(ValidateEntity validate) {
        this.validates.add(validate);
        return (Builder) this;
    }

    public Builder withValidates(List<ValidateEntity> validates) {
        for (ValidateEntity validate : validates) {
            this.validates.remove(validate);
        }
        this.validates.addAll(validates);
        return (Builder) this;
    }

    public Builder withError(BaseException error) {
        this.error = error;
        return (Builder) this;
    }

    public Builder withData(RestEntity<T> data) {
        this.data = data;
        return (Builder) this;
    }


    public ResultEntity<T> build() {
        return new ResultEntity<>(this.status, this.validates, this.error, this.data);
    }

}

