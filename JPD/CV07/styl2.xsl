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
        <dd><xsl:value-of select="shell"/></dd>
    </xsl:template>

</xsl:stylesheet>