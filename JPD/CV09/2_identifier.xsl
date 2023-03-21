<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" indent="yes" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <xsl:apply-templates select="uzivatele/osoba">
            <xsl:sort select="@id" order="descending"/>
        </xsl:apply-templates>
    </xsl:template>

    <xsl:template match="uzivatele/osoba">
        <xsl:if test="kategorie = 'sluzba'">
            <xsl:value-of select="concat(@id, ' ', jmeno)"/>
            <xsl:text>&#xa;</xsl:text>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>