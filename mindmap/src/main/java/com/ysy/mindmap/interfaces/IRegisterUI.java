package com.ysy.mindmap.interfaces;

import com.ysy.mindmap.bases.IUI;

/**
 * Created by Sylvester on 17/5/3.
 */

public interface IRegisterUI extends IUI {
    void onRegisterSuccess();

    void onRegisterFail(String errorMsg);
}
