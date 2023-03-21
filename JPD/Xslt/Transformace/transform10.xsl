<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html lang="cs">
            <head>
                <title><xsl:text>Seznam vozidel</xsl:text></title>
                <meta charset="UTF-8"/>
            </head>
            <body>
                <ul>
                    <xsl:apply-templates select="//motor">
                        <xsl:sort select="../../nazev"/>
                        <xsl:sort select="../nazev"/>
                        <xsl:sort select="objem"/>
                    </xsl:apply-templates>
                </ul>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="//motor">
        <xsl:if test="@palivo = 'benzin'">
            <li>
                <xsl:value-of select="../../nazev"/>
                <xsl:text> </xsl:text>
                <xsl:value-of select="../nazev"/>
                <xsl:text> </xsl:text>
                <xsl:value-of select="nazev"/>
            </li>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>