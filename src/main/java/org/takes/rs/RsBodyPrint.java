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
package org.takes.rs;

import java.io.IOException;
import org.cactoos.Text;
import org.takes.Response;

/**
 * Response body decorator that can print an entire textual (!)
 * body response in HTTP format.
 *
 * <p>This class is mostly used for testing. Don't use it for
 * production code, since it will break the binary content of your
 * HTTP response. It's only suitable for texts in HTTP responses.</p>
 *
 * <p>The class is immutable and thread-safe.
 *
 * @since 2.0
 */
public final class RsBodyPrint implements Text {

    /**
     * The HTTP Response.
     */
    private final Response response;

    /**
     * Ctor.
     *
     * @param res Original response
     */
    public RsBodyPrint(final Response res) {
        this.response = res;
    }

    @Override
    public String asString() throws IOException {
        return new RsPrint(this.response).printBody();
    }
}
