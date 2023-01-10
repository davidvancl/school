<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html lang="cs">
            <head>
                <title>Seznam měst</title>
            </head>
            <body>
                <ul>
                    <xsl:apply-templates select="//mesto">
                        <xsl:sort select="obyvatel"/>
                    </xsl:apply-templates>
                </ul>
                <ul>
                    <li>
                        <xsl:text>Celkem měst: </xsl:text>
                        <xsl:value-of select="count(//mesto)"/>
                    </li>
                </ul>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="//mesto">
        <xsl:if test="obyvatel > 20000">
            <li>
                <xsl:value-of select="nazev"/>
                <xsl:text>, </xsl:text>
                <xsl:value-of select="obyvatel"/>
                <xsl:text>, id=</xsl:text>
                <xsl:value-of select="@id"/>
            </li>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>