package com.qunar.handler;

import com.qunar.model.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ReplayingDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Date: 2014-02-24 19:13
 *
 * @author sen.chai
 */
public class TimeDecoder extends ReplayingDecoder {

    private static final Logger logger = LoggerFactory.getLogger(TimeDecoder.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("channelActive before writeAndFlush");
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
        logger.debug("channelActive after writeAndFlush");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime m = (UnixTime) msg;
        logger.debug("time: {}", m);
        ctx.close();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        logger.debug("before decode");
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new UnixTime(in.readInt()));
        logger.debug("after decode");
    }
}
