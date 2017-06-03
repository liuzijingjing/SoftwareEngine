package com.ysy.mindmap.presenters;

import com.ysy.mindmap.bases.BasePresenter;
import com.ysy.mindmap.interfaces.IMainUI;
import com.ysy.mindmap.models.datas.DataUser;
import com.ysy.mindmap.utils.dbconnector.SQLCallback;
import com.ysy.mindmap.utils.dbconnector.SQLClient;
import com.ysy.mindmap.utils.dbconnector.SQLEntity;
import com.ysy.mindmap.utils.dbconnector.SQLErrorConstant;
import com.ysy.mindmap.utils.dbconnector.SQLRequest;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sylvester on 17/5/3.
 */

public class MainPresenter extends BasePresenter<IMainUI> {

    public void queryUser(final long uid) {
        SQLEntity<String> entity = new SQLEntity<>();
        entity.setSQL("select sex, avatar, nickname, intro, teams, create_time, update_time" +
                " from user where uid = " + uid);
        entity.setType(SQLRequest.RequestType.SELECT);
        SQLClient.invokeStringRequest(getUI().getActivity(), entity, new SQLCallback<ResultSet>() {
            @Override
            public void onSuccess(ResultSet res) {
                DataUser user = new DataUser();
                user.setUid(uid);
                try {
                    while (res.next()) {
                        user.setSex(res.getByte(1));
                        user.setAvatar(res.getString(2));
                        user.setNickname(res.getString(3));
                        user.setIntro(res.getString(4));
                        user.setTeams(res.getString(5));
                        user.setCreateTime(res.getString(6));
                        user.setUpdateTime(res.getString(7));
                    }
                    getUI().onQueryUserSuccess(user);
                } catch (SQLException e) {
                    getUI().onQueryUserFail(SQLErrorConstant.getErrorMsg(SQLErrorConstant.ERROR_SYSTEM));
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(int errorCode) {
                getUI().onQueryUserFail(SQLErrorConstant.getErrorMsg(errorCode));
            }
        });
    }
}
