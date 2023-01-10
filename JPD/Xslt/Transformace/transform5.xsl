<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html lang="cs">
            <head>
                <title><xsl:text>Seznam místností</xsl:text></title>
            </head>
            <body>
                <xsl:apply-templates select="data/mistnosti"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="data/mistnosti">
        <ul>
            <xsl:apply-templates select="mistnost">
                <xsl:sort select="nazev"/>
            </xsl:apply-templates>
        </ul>
    </xsl:template>

    <xsl:template match="mistnost">
        <xsl:variable name="mid">
            <xsl:value-of select="@id"/>
        </xsl:variable>
        <xsl:if test="patro = 1">
            <li>
                <strong><xsl:value-of select="nazev"/></strong>
                <xsl:text>,&#xA; budova </xsl:text>
                <xsl:value-of select="budova"/>
                <xsl:text>,</xsl:text>
                <xsl:value-of select="patro"/>
                <xsl:text>. patro, &#xA;</xsl:text>

                <xsl:for-each select="//data/zamestnanci/osoba[kancelar = $mid]">
                    <xsl:value-of select="jmeno"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="prijmeni"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
            </li>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>