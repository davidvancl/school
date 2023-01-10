<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:text>Seznam katedra</xsl:text></title>
            </head>
            <body>
                <xsl:apply-templates select="sit/katedra"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="sit/katedra">
        <h2><xsl:value-of select="nazev"/></h2>
        <ul>
            <xsl:apply-templates select="host"/>
        </ul>
    </xsl:template>

    <xsl:template match="host">
        <xsl:variable name="pdsit">
            <xsl:value-of select="podsit"/>
        </xsl:variable>
        <xsl:if test="@typ = 'pc'">
            <li>
                <xsl:value-of select="jmeno"/>
                <xsl:text>.</xsl:text>
                <xsl:value-of select="../domena"/>
                <xsl:text> (</xsl:text>
                <xsl:value-of select="@typ"/>
                <xsl:text>) </xsl:text>
                <xsl:value-of select="//sit/podsit[@id = $pdsit]/ip"/>
                <xsl:text>.</xsl:text>
                <xsl:value-of select="ip"/>
            </li>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>