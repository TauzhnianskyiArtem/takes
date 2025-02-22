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
package org.takes.rs.xe;

import com.jcabi.matchers.XhtmlMatchers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link XeWhen}.
 * @since 0.13
 */
final class XeWhenTest {

    @Test
    void buildsXmlResponse() throws IOException {
        MatcherAssert.assertThat(
            IOUtils.toString(
                new RsXembly(
                    new XeAppend(
                        "test",
                        new XeWhen(
                            true,
                            new XeDate()
                        )
                    )
                ).body(),
                StandardCharsets.UTF_8
            ),
            XhtmlMatchers.hasXPaths(
                "/test[@date]"
            )
        );
    }

    @Test
    void buildsXmlResponseFromPositiveCondition() throws IOException {
        MatcherAssert.assertThat(
            IOUtils.toString(
                new RsXembly(
                    new XeAppend(
                        "positive",
                        new XeWhen(
                            true,
                            new XeDate(),
                            new XeMemory()
                        )
                    )
                ).body(),
                StandardCharsets.UTF_8
            ),
            XhtmlMatchers.hasXPaths(
                "/positive[@date]"
            )
        );
    }

    @Test
    void buildsXmlResponseFromNegativeCondition() throws Exception {
        MatcherAssert.assertThat(
            IOUtils.toString(
                new RsXembly(
                    new XeAppend(
                        "negative",
                        new XeWhen(
                            false,
                            () -> new XeDate(),
                            () -> new XeMemory()
                        )
                    )
                ).body(),
                StandardCharsets.UTF_8
            ),
            XhtmlMatchers.hasXPaths(
                "/negative/memory"
            )
        );
        new Assertion<>(
            "Must be empty when negative condition without negative source",
            new TextOf(
                new RsXembly(
                    new XeAppend(
                        "negative",
                        new XeWhen(
                            false,
                            new XeDate()
                        )
                    )
                ).body()
            ).asString(),
            XhtmlMatchers.hasXPaths(
                "/negative"
            )
        ).affirm();
    }

}
