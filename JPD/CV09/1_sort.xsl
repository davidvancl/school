<?xml version="1.0" encoding="utf-8"?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <uzivatele>
            <xsl:apply-templates select="uzivatele/osoba">
                <xsl:sort select="jmeno"/>
            </xsl:apply-templates>
        </uzivatele>
    </xsl:template>

    <xsl:template match="uzivatele/osoba">
        <xsl:text>&#xa;</xsl:text>
        <osoba>
            <xsl:copy-of select="@*|*|text()" />
        </osoba>
    </xsl:template>
</xsl:transform>
