package com.tianqi.common.result.rest.builder;

import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.enums.business.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.entity.RestEntity;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.result.rest.entity.ValidateEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yuantianqi
 */
public abstract class ResultBuilder<T, Builder extends ResultBuilder> {
    private BaseEnum status;
    private final List<ValidateEntity> validates = new ArrayList<>();
    private BaseException error;
    private RestEntity<T> data;


    public Builder withStatus(final BaseEnum status) {
        this.status = status;
        return (Builder) this;
    }

    public Builder withValidate(final ValidateEntity validate) {
        this.validates.add(validate);
        return (Builder) this;
    }

    public Builder withValidates(final List<ValidateEntity> validates) {
        for (final ValidateEntity validate : validates) {
            this.validates.remove(validate);
        }
        this.validates.addAll(validates);
        return (Builder) this;
    }

    public Builder withError(final BaseException error) {
        this.error = error;
        return (Builder) this;
    }

    protected Builder withData(final RestEntity<T> data) {
        this.data = data;
        return (Builder) this;
    }


    public ResultEntity<T> build() {
        if (status == null) {
            status = StatusEnum.OK;
        }
        return new ResultEntity<>(this.status, this.validates, this.error,
                this.data);
    }

}

