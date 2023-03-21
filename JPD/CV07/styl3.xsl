<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:variable name="title">
        <xsl:text>Seznam uživatelů</xsl:text>
    </xsl:variable>

    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:value-of select="$title"/></title>
            </head>
            <body>
                <h1><xsl:value-of select="$title"/></h1>
                <dl>
                    <xsl:apply-templates select="uzivatele/osoba"/>
                </dl>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="uzivatele/osoba">
        <dt><strong><xsl:value-of select="jmeno"/></strong></dt>
        <dd>
            <xsl:text>shell: </xsl:text><xsl:value-of select="shell"/><br/>
            <xsl:text>web: </xsl:text>
            <xsl:variable name="hrefValue"><xsl:text>/~</xsl:text><xsl:value-of select="jmeno"/></xsl:variable>
            <a href="/~mail">
                <xsl:attribute name="href">
                    <xsl:value-of select="$hrefValue"/>
                </xsl:attribute>
                <xsl:value-of select="$hrefValue"/>
            </a>
        </dd>
    </xsl:template>

</xsl:stylesheet>