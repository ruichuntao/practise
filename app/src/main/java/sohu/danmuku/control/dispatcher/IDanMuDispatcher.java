package sohu.danmuku.control.dispatcher;

import sohu.danmuku.model.DanMuModel;
import sohu.danmuku.model.channel.DanMuChannel;

public interface IDanMuDispatcher {

    void dispatch(DanMuModel iDanMuView, DanMuChannel[] danMuChannels);

}
