/*
 * Chromer
 * Copyright (C) 2017 Arunkumar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package arun.com.chromer.data.webarticle;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import arun.com.chromer.data.webarticle.model.WebArticle;
import arun.com.chromer.util.parser.RxParser;
import rx.Observable;

/**
 * Network store which freshly parses website data for a given URL.
 */
@Singleton
public class WebArticleNetworkStore implements WebArticleStore {

    @Inject
    WebArticleNetworkStore() {
    }

    @NonNull
    @Override
    public Observable<WebArticle> getWebArticle(@NonNull String url) {
        return RxParser.parseArticle(url)
                .flatMap(urlArticlePair -> {
                    if (urlArticlePair.second != null) {
                        return Observable.just(WebArticle.fromArticle(urlArticlePair.second));
                    } else {
                        return Observable.just(null);
                    }
                });
    }

    @NonNull
    @Override
    public Observable<WebArticle> saveWebArticle(@NonNull WebArticle webSite) {
        return Observable.empty();
    }
}
