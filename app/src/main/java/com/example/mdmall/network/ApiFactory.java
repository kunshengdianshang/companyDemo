/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.mdmall.network;


import com.example.mdmall.XynApi;

/**
 * Api generate
 * Created by staffy on 8/9/15.
 */
public class ApiFactory {

    protected static final Object monitor = new Object();
    static XynApi mXynSingleton = null;
    public static final int PAGE_SIZE = 10;

    //遛狗api
    public static XynApi getXynSingleton() {
        synchronized (monitor) {
            if (mXynSingleton == null) {
                mXynSingleton = new MyRetrofit().geXynService();
            }
            return mXynSingleton;
        }
    }
}
