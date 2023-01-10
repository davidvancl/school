<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <ul>
            <xsl:apply-templates select="//pojem">
                <xsl:sort select="text()"/>
            </xsl:apply-templates>
        </ul>
    </xsl:template>

    <xsl:template match="//pojem">
        <li>
            <xsl:if test="@rodic != ''">
                <xsl:value-of select="@rodic"/>
                <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:value-of select="text()"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="ancestor::sekce[1]/nadpis"/>
        </li>
    </xsl:template>

</xsl:stylesheet>