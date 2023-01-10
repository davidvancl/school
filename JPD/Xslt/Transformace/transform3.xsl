<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html lang="cs">
            <head>
                <title>Seznam měst</title>
            </head>
            <body>
                <xsl:apply-templates select="adresar">
                    <xsl:sort select="nazev"/>
                </xsl:apply-templates>
            </body>
        </html>

    </xsl:template>

    <xsl:template match="adresar">
        <xsl:choose>
            <xsl:when test="soubor/nazev != ''">
                <p><xsl:value-of select="nazev"/></p>
                <ul>
                    <xsl:apply-templates select="soubor">
                        <xsl:sort select="nazev"/>
                    </xsl:apply-templates>
                </ul>
            </xsl:when>
            <xsl:otherwise>
                <xsl:choose>
                    <xsl:when test="../nazev">
                        <li>
                            <p><xsl:value-of select="nazev"/></p>
                            <ul>
                                <xsl:apply-templates select="adresar">
                                    <xsl:sort select="nazev"/>
                                </xsl:apply-templates>
                            </ul>
                        </li>
                    </xsl:when>
                    <xsl:otherwise>
                        <p><xsl:value-of select="nazev"/></p>
                        <ul>
                            <xsl:apply-templates select="adresar">
                                <xsl:sort select="nazev"/>
                            </xsl:apply-templates>
                        </ul>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="soubor">
        <li>
            <xsl:value-of select="nazev"/>
            <xsl:if test="velikost">
                <xsl:text>(</xsl:text>
                <xsl:value-of select="velikost"/>
                <xsl:text>)</xsl:text>
            </xsl:if>

            <xsl:if test="../nazev">
                <xsl:text>/</xsl:text><xsl:value-of select="../nazev"/>
            </xsl:if>
            <xsl:if test="../../nazev">
                <xsl:text>/</xsl:text><xsl:value-of select="../../nazev"/>
            </xsl:if>
            <xsl:if test="../../../nazev">
                <xsl:text>/</xsl:text><xsl:value-of select="../../../nazev"/>
            </xsl:if>
        </li>
    </xsl:template>
</xsl:stylesheet>