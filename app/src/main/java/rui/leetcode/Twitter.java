package rui.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

class Twitter {
    Map<Integer, TreeSet<TweetBean>> sum;
    TreeMap<Integer, List<TweetBean>> map;
    int TIME = 0;

    class TweetBean implements Comparable{
        int tweetId;
        int userId;
        int time;

        public TweetBean(int tweetId,int userId,int time) {
            this.tweetId = tweetId;
            this.userId = userId;
            this.time = time;
        }

        @Override
        public int compareTo(Object o) {
            return time - ((TweetBean)o).time;
        }
    }


    /** Initialize your data structure here. */
    public Twitter() {
        map = new TreeMap<>();
        sum = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new ArrayList<TweetBean>());
        }
        map.get(userId).add(new TweetBean(tweetId, userId, TIME++));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list = new ArrayList<>();
        int idx = 0;
        for (TweetBean bean : map.get(userId)) {
            idx++;
            list.add(bean.tweetId);
            if (idx == 10) break;
        }
        return list;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!sum.containsKey(followerId)) {
            sum.put(followerId, new TreeSet<>());
        }
        if (!map.containsKey(followeeId)) {
            return;
        }
        for (TweetBean bean : map.get(followeeId)) {
            sum.get(followerId).add(bean);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!sum.containsKey(followerId)) {
            sum.put(followerId, new TreeSet<>());
        }
        if (!map.containsKey(followeeId)) {
            return;
        }
        for (TweetBean bean : map.get(followeeId)) {
            sum.get(followerId).remove(bean);
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        twitter.getNewsFeed(1);

// 用户1关注了用户2.
        twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
        twitter.getNewsFeed(1);

// 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
        twitter.getNewsFeed(1);

    }
}
