/*
 Copyright (c) 2007-2009, Yusuke Yamamoto
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 * Neither the name of the Yusuke Yamamoto nor the
 names of its contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package twitter4j.examples.signin;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class SigninServlet extends HttpServlet {

    private static final long serialVersionUID = -6205814293093350242L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey("J6nLFi02BXmI4q3JDIV2CknsB");
            builder.setOAuthConsumerSecret("Pz3XUSdNLjr1XQ1N8L6PfOytzMpTBkdXRX7olJUbGDf1gGC3Jh");
            builder.setOAuthAccessToken("405731273-GquJlFMt9mMljMeMK0IHGsQclR6GwCSovDf3HGhN");
            builder.setOAuthAccessTokenSecret("G4Mipo2D54n67P448TzyOIpI7Ys1adkiffBi3vpObYopm");
            Configuration configuration = builder.build();
            Twitter twitter = new TwitterFactory(configuration).getInstance();
            request.getSession().setAttribute("twitter", twitter);
            System.out.println("getTwitterDetails(Twitter twitter) ::: \n " + getTwitterDetails(twitter));
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/callback");
            System.out.println("callbackURL.toString() :: " + callbackURL.toString());
            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            System.out.println("requestToken.getAuthenticationURL() :: " + requestToken.getAuthenticationURL());
            response.sendRedirect(requestToken.getAuthenticationURL());

        } catch (TwitterException e) {
            throw new ServletException(e);
        }

    }

    String getTwitterDetails(Twitter twitter) {
        StringBuilder data = new StringBuilder();

        try {
            long i = twitter.getId();
            User u = twitter.showUser(i);
            data.append("{");

            data.append(u.getId() + " , ");

            data.append(u.getName() + " , ");

            data.append(u.getScreenName() + " , ");

            data.append(u.getLocation() + " , ");

            data.append(u.getDescription() + " , ");

            data.append(u.isContributorsEnabled() + " , ");

            data.append(u.getProfileImageURL() + " , ");

            data.append(u.getBiggerProfileImageURL() + " , ");

            data.append(u.getMiniProfileImageURL() + " , ");

            data.append(u.getOriginalProfileImageURL() + " , ");

            data.append(u.getProfileImageURLHttps() + " , ");

            data.append(u.getBiggerProfileImageURLHttps() + " , ");

            data.append(u.getMiniProfileImageURLHttps() + " , ");

            data.append(u.getOriginalProfileImageURLHttps() + " , ");

            data.append(u.isDefaultProfileImage() + " , ");

            data.append(u.getURL() + " , ");

            data.append(u.isProtected() + " , ");

            data.append(u.getFollowersCount() + " , ");

            data.append(u.getStatus() + " , ");

            data.append(u.getProfileBackgroundColor() + " , ");

            data.append(u.getProfileTextColor() + " , ");

            data.append(u.getProfileLinkColor() + " , ");

            data.append(u.getProfileSidebarFillColor() + " , ");

            data.append(u.getProfileSidebarBorderColor() + " , ");

            data.append(u.isProfileUseBackgroundImage() + " , ");

            data.append(u.isDefaultProfile() + " , ");

            data.append(u.isShowAllInlineMedia() + " , ");

            data.append(u.getFriendsCount() + " , ");

            data.append(u.getCreatedAt() + " , ");

            data.append(u.getFavouritesCount() + " , ");

            data.append(u.getUtcOffset() + " , ");

            data.append(u.getTimeZone() + " , ");

            data.append(u.getProfileBackgroundImageURL() + " , ");

            data.append(u.getProfileBackgroundImageUrlHttps() + " , ");

            data.append(u.getProfileBannerURL() + " , ");

            data.append(u.getProfileBannerRetinaURL() + " , ");

            data.append(u.getProfileBannerIPadURL() + " , ");

            data.append(u.getProfileBannerIPadRetinaURL() + " , ");

            data.append(u.getProfileBannerMobileURL() + " , ");

            data.append(u.getProfileBannerMobileRetinaURL() + " , ");

            data.append(u.isProfileBackgroundTiled() + " , ");

            data.append(u.getLang() + " , ");

            data.append(u.getStatusesCount() + " , ");

            data.append(u.isGeoEnabled() + " , ");

            data.append(u.isVerified() + " , ");

            data.append(u.isTranslator() + " , ");

            data.append(u.getListedCount() + " , ");

            data.append(u.isFollowRequestSent() + " , ");
            data.append("}");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data.toString();
    }
}
