/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.facets.auth;

import lombok.EqualsAndHashCode;
import org.takes.Request;
import org.takes.Response;
import org.takes.misc.Opt;

/**
 * Fake pass.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @since 0.9
 */
@EqualsAndHashCode
public final class PsFake implements Pass {

    /**
     * Should we authenticate a user?
     */
    private final boolean condition;

    /**
     * Ctor.
     * @param cond Condition
     */
    public PsFake(final boolean cond) {
        this.condition = cond;
    }

    @Override
    public Opt<Identity> enter(final Request request) {
        final Opt<Identity> user;
        if (this.condition) {
            user = new Opt.Single<>(
                new Identity.Simple("urn:test:1")
            );
        } else {
            user = new Opt.Empty<>();
        }
        return user;
    }

    @Override
    public Response exit(final Response response, final Identity identity) {
        return response;
    }
}
