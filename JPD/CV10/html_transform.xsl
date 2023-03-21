<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="synopsis">
        <html xmlns="http://www.w3.org/1999/xhtml">
            <body>
                <table>
                <tr>
                    <td>#</td>
                    <td><xsl:text disable-output-escaping="yes">dějství</xsl:text></td>
                    <td><xsl:text disable-output-escaping="yes">scéna</xsl:text></td>
                    <td><xsl:text>lokace</xsl:text></td>
                </tr>
                <xsl:for-each select="act/scene">
                    <xsl:variable name="i" select="position()" />
                    <tr>
                        <td><xsl:value-of select="$i"/><xsl:text>. </xsl:text></td>
                        <td><xsl:value-of select="translate(../act_number, 'act', '')"/></td>
                        <td><xsl:value-of select="translate(scene_number, 'scene', '')"/></td>
                        <td><xsl:value-of select="location"/></td>
                    </tr>
                </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>