<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:text>Seznam filmů</xsl:text></title>
            </head>
            <body>
                <table>
                    <xsl:apply-templates select="filmoteka/film">
                        <xsl:sort select="nazev"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="filmoteka/film">
        <xsl:if test="herci/herec[text() = 'Harrison Ford']">
            <tr>
                <td><xsl:value-of select="@id"/></td>
                <td><xsl:value-of select="nazev"/></td>
                <td><xsl:value-of select="rezie"/></td>
                <td><xsl:value-of select="count(herci/herec)"/></td>
            </tr>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>