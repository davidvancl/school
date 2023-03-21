<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:variable name="mainTitle"><xsl:text>Katalog CD</xsl:text></xsl:variable>

    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
                <title>
                    <xsl:value-of select="$mainTitle"/>
                </title>
            </head>
            <body>
                <h2><xsl:value-of select="$mainTitle"/></h2>
                <table>
                    <tr>
                        <th><xsl:text>titul</xsl:text></th>
                        <th><xsl:text>umělec</xsl:text></th>
                    </tr>

                    <xsl:for-each select="catalog/cd">
                        <tr>
                            <td><xsl:value-of select="title"/></td>
                            <td><xsl:value-of select="artist"/></td>
                        </tr>
                        <xsl:sort select="title" />
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>