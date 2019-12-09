package rui.mvp.view;

import rui.mvp.presenter.BasePresenter;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
